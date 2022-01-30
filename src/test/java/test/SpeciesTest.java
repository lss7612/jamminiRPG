package test;


import org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;

import org.junit.Test;

import com.rpg.jammini.object.skill.Skill;
import com.rpg.jammini.object.species.Elf;
import com.rpg.jammini.object.species.Human;
import com.rpg.jammini.object.species.Oak;
import com.rpg.jammini.object.species.Species;
import com.rpg.jammini.object.weapon.Weapon;


public class SpeciesTest {

	private static final double DELTA = 1e-15;
	
	@Test
	public void levelUpTest() {
		
		Species humanLv1 = new Human();
		Species humanLv2 = new Human(); 
		humanLv2.levelUp(new ArrayList<String>());
		assertEquals(humanLv1.getDff() + 1, humanLv2.getDff(),DELTA);
		
		Species oakLv1 = new Oak();
		Species oakLv2 = new Oak(); 
		oakLv2.levelUp(new ArrayList<String>());
		assertEquals(oakLv1.getAtt() + 1, oakLv2.getAtt(),DELTA);
		
		Species elfLv1 = new Elf();
		Species elfLv2 = new Elf(); 
		elfLv2.levelUp(new ArrayList<String>());
		assertEquals(elfLv1.getDex() + 1, elfLv2.getDex(),DELTA);
		
	}
	
	/**
	 * 무기랑 스킬 뭘 먼저 사용하든 결국 같은 스텟을 가져아함
	 */
	@Test
	public void weapon_skill_order_test() {
		
		Weapon w = Weapon.ShortSword;
		Skill s = Skill.Stream;
		
		Species h1 = new Human();
		Species h2 = new Human();
		
		h1.setWeapon(w);
		h1.setActive(s);
		
		h2.setActive(s);
		h2.setWeapon(w);
		
		assertEquals(h1, h2);
		
	}
	
}
