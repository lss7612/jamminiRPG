package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.rpg.jammini.object.skill.Skill;
import com.rpg.jammini.object.species.Human;
import com.rpg.jammini.object.species.Monster;
import com.rpg.jammini.object.weapon.Weapon;

public class RequirmentsTest {

	private static final double DELTA = 1e-15;
	
	@Test
	public void requirement1() {
		
		List<String> logList = new ArrayList<String>();
		
		//캐릭터는 무기를 착용하여 몬스터에게 공격할 수 있다.
		Human human = new Human();
		human.setWeapon(Weapon.LongSword); //무기장착
		Monster monster = new Monster(); //몬스터 공격
		monster.setAvoidRate(0); //몬스터 무조건 맞도록 설정
		human.attack(monster, logList);
		
		
		float damage = human.getAtt() - monster.getDff();
		assertEquals(damage, monster.getMaxHp() - monster.getHp(), DELTA);
		
		//딜레이중 공격 못함 hp 그대로
		human.attack(monster, logList);
		assertEquals(damage, monster.getMaxHp() - monster.getHp(), DELTA);
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException ignore) {}
		
		//딜레이 이후 공격 가능 데미지 2배
		human.attack(monster, logList);
		assertEquals(damage * 2, monster.getMaxHp() - monster.getHp(), DELTA);
		
	}
	
	@Test
	public void requirement2() {
		List<String> logList = new ArrayList<String>();
		
		
		Human human = new Human();
		//본인이 습득할 수 없는 다른종족 스킬은 사용할 수 없음
		human.useSkill(Skill.Anger, logList);
		assertEquals(human.getActive(), Skill.NULL);
		
		//본인 레벨에 맞지 않는 스킬은 사용할 수 없음
		human.useSkill(Skill.Invisible, logList);
		assertEquals(human.getActive(), Skill.NULL);
		
		//만렙에는 스킬 사용 가능
		while(human.getLevel() != human.levelMax) {
			human.levelUp(logList);
		}
		human.useSkill(Skill.Invisible, logList);
		assertEquals(human.getActive(), Skill.Invisible);
		
//		logList.stream().forEach(System.out::println);
		
	}
	
	@Test
	public void requirement3() {
		//몬스터 또한 캐릭터를 공격할 수 있다.
		List<String> logList = new ArrayList<String>();
		Monster monster = new Monster();
		Human human = new Human();
		human.setAvoidRate(0);
		monster.attack(human, logList);
		
		float damage = monster.getAtt() - human.getDff();
		assertEquals(damage, human.getMaxHp()-human.getHp(),DELTA);
		
//		logList.stream().forEach(System.out::println);
	}
	
	@Test
	public void requirement4() {
		//회피율에 따라 공격 또는 반격이 무시될 수 있다.
		List<String> logList = new ArrayList<String>();
		Human human = new Human();
		Monster monster = new Monster();
		
		human.setAvoidRate(1);
		monster.setAvoidRate(1);
		monster.setCounterRate(100);
		
		human.attack(monster, logList);
		monster.attack(human, logList);
		
		logList.stream().forEach(System.out::println);
		assertEquals(0, human.getMaxHp()-human.getHp(),DELTA);
		assertEquals(0, monster.getMaxHp()-monster.getHp(),DELTA);
	}
	
	
	
}
