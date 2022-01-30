package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.rpg.jammini.object.skill.Skill;
import com.rpg.jammini.object.species.Elf;
import com.rpg.jammini.object.species.Human;
import com.rpg.jammini.object.species.Oak;

public class SkillTest {

	private static final double DELTA = 1e-15;
	
	@Test
	public void stream_test() {
		
		Human human = new Human();
		float att_origin = human.getAtt();
		
		human.useSkill(Skill.Stream, new ArrayList<>());
		float att_after = human.getAtt();
		
		assertEquals(att_origin * 1.2, att_after, DELTA);
		
	}
	
	@Test
	public void elusion_test() {
		
		Elf elf = new Elf();
		float avoid_origin = elf.getAvoidRate();
		
		elf.useSkill(Skill.Elusion, new ArrayList<>());
		float avoid_after = elf.getAvoidRate();
		
		assertEquals(avoid_origin * 1.3f, avoid_after, DELTA);
		
	}
	
	@Test
	public void guard_test() {
		
		Human human = new Human();
		float origin = human.getDff();
		
		human.useSkill(Skill.Guard, new ArrayList<>());
		float after = human.getDff();
		
		assertEquals(origin * 1.3f, after, DELTA);
	}
	
	@Test
	public void anger_test() {
		
		Oak oak = new Oak();
		float origin_att = oak.getAtt();
		float origin_dff = oak.getDff();
		
		oak.useSkill(Skill.Anger, new ArrayList<>());
		float after_att = oak.getAtt();
		float after_dff = oak.getDff();
		
		assertEquals(origin_att * 1.5f, after_att, DELTA);
		assertEquals(origin_dff * 0.9f, after_dff, DELTA);
	}
	
	@Test
	public void invisible_test() {
		
		Human human = new Human();
		human.useSkill(Skill.Invisible, new ArrayList<>());
		human.setAvoidRate(0);
		
		float origin_hp = human.getHp();
		
		Oak oak = new Oak();
		oak.attack(human, new ArrayList<>());
		
		float after_hp = human.getHp();
		
		assertEquals(origin_hp, after_hp, DELTA);
		
	}
	
	@Test
	public void rapid_test() {
		
		Elf elf = new Elf();
		float origin_dex = elf.getDex();
		
		elf.useSkill(Skill.Rapid, new ArrayList<>());
		float after_dex = elf.getDex();
		
		assertEquals(origin_dex * 5, after_dex, DELTA);
		
	}
	
	@Test
	public void frenzy_test() {
		
		Oak oak = new Oak();
		float origin_dex = oak.getDex();
		
		oak.useSkill(Skill.Frenzy, new ArrayList<>());
		float after_dex = oak.getDex();
		
		assertEquals(origin_dex * 5, after_dex, DELTA);
		
	}
	
	@Test
	public void cannot_heal_over_max_hp() {
		Human human = new Human();
		human.useSkill(Skill.Heal, new ArrayList<String>());
		human.useSkill(Skill.Heal, new ArrayList<String>());
		human.useSkill(Skill.Heal, new ArrayList<String>());
		assertEquals(human.getHp(), human.getMaxHp(), DELTA);
	}

}
