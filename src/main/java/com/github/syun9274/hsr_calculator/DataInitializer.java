package com.github.syun9274.hsr_calculator;

import com.github.syun9274.hsr_calculator.dto.Buff;
import com.github.syun9274.hsr_calculator.dto.DamageInfo;
import com.github.syun9274.hsr_calculator.dto.Trace;
import com.github.syun9274.hsr_calculator.model.entity.*;
import com.github.syun9274.hsr_calculator.model.entity.Character;
import com.github.syun9274.hsr_calculator.model.enums.*;
import com.github.syun9274.hsr_calculator.repository.CharacterRepository;
import com.github.syun9274.hsr_calculator.repository.EnemyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final CharacterRepository characterRepository;
    private final EnemyRepository enemyRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeData() {
        // 적 정보 초기화 로직
        if (enemyRepository.count() == 0) {
            Enemy enemy = new Enemy();
            enemy.setName("불의 재난");
            enemy.setLevel(74);
            enemy.setBaseHp(16209);
            enemy.setBaseAtk(482);
            enemy.setBaseDef(940);
            enemy.setWeaknessElements(List.of(Element.ICE, Element.WIND, Element.IMAGINARY));
            enemy.setResistElements(List.of(Element.FIRE));
            enemyRepository.save(enemy);
        }

        // 캐릭터 정보 초기화 로직
        if (characterRepository.count() == 0) {
            Character character1 = getClara();
            Character character2 = getTribbie();
            Character character3 = getSunday();
            Character character4 = getCipher();

            characterRepository.save(character1);
            characterRepository.save(character2);
            characterRepository.save(character3);
            characterRepository.save(character4);
        }
    }

    private static Character getClara() {
        Character character = new Character();
        character.setName("클라라");
        character.setLevel(80);
        character.setBaseHp(2406);
        character.setBaseAtk(1319);
        character.setBaseDef(882);
        character.setSpd(90);
        character.setElement(Element.PHYSICAL);
        character.setFatePath(FatePath.DESTRUCTION);
        character.setScalingAttribute(StatType.ATK);

        // 일반 공격
        BasicAttack basicAttack = new BasicAttack();
        basicAttack.setCharacter(character);
        basicAttack.setDamageInfo(new DamageInfo(1.0));
        character.setBasicAttack(basicAttack);

        // 전투 스킬
        Skill skill = new Skill();
        skill.setCharacter(character);
        skill.setEffectType(EffectType.DAMAGE);
        skill.setDamageInfo(new DamageInfo(1.26));
        character.setSkill(skill);

        // 필살기
        Ultimate ultimate = new Ultimate();
        ultimate.setCharacter(character);
        ultimate.setEffectType(EffectType.SUPPORT);
        ultimate.setBuffs(List.of(new Buff(BuffType.DAMAGE_BOOST, 152.0, Target.SELF)));
        character.setUltimate(ultimate);

        // 특성
        Talent talent = new Talent();
        talent.setCharacter(character);
        character.setTalent(talent);

        // 행적
        character.setTraces(Arrays.asList(
                new Trace(TraceType.MAJOR_PASSIVE, List.of(
                        new Buff(BuffType.EFFECT_RES_PERCENT, 35.0)
                )),
                new Trace(TraceType.MINOR_NODE, Arrays.asList(
                        new Buff(BuffType.ATK_PERCENT, 4.0),

                        new Buff(BuffType.PHYSICAL_DAMAGE_BOOST, 3.2),
                        new Buff(BuffType.ATK_PERCENT, 4.0),
                        new Buff(BuffType.HP_PERCENT, 4.0),

                        new Buff(BuffType.ATK_PERCENT, 6.0),
                        new Buff(BuffType.PHYSICAL_DAMAGE_BOOST, 4.8),
                        new Buff(BuffType.ATK_PERCENT, 6.0),

                        new Buff(BuffType.HP_PERCENT, 6.0),
                        new Buff(BuffType.PHYSICAL_DAMAGE_BOOST, 6.4),
                        new Buff(BuffType.ATK_PERCENT, 8.0)
                ))
        ));

        character.setMemosprite(null);
        return character;
    }

    private static Character getTribbie() {
        Character character = new Character();
        character.setName("트리비");
        character.setLevel(80);
        character.setBaseHp(2317);
        character.setBaseAtk(1053);
        character.setBaseDef(1124);
        character.setSpd(96);
        character.setElement(Element.QUANTUM);
        character.setFatePath(FatePath.HARMONY);
        character.setScalingAttribute(StatType.HP);

        // 일반 공격
        BasicAttack basicAttack = new BasicAttack();
        basicAttack.setCharacter(character);
        basicAttack.setDamageInfo(new DamageInfo(0.3));
        character.setBasicAttack(basicAttack);

        // 전투 스킬
        Skill skill = new Skill();
        skill.setCharacter(character);
        skill.setEffectType(EffectType.SUPPORT);
        skill.setBuffs(List.of(new Buff(BuffType.RES_PEN, 24.0, Target.ALLY)));
        character.setSkill(skill);

        // 필살기
        Ultimate ultimate = new Ultimate();
        ultimate.setCharacter(character);
        ultimate.setEffectType(EffectType.HYBRID);
        ultimate.setDamageInfo(new DamageInfo(0.3));
        ultimate.setBuffs(List.of(new Buff(BuffType.DAMAGE_TAKEN_INCREASE, 30.0, Target.ENEMY)));
        character.setUltimate(ultimate);

        // 특성
        Talent talent = new Talent();
        talent.setCharacter(character);
        talent.setEffectType(EffectType.DAMAGE);
        talent.setDamageInfo(new DamageInfo(0.18));
        character.setTalent(talent);

        // 행적
        character.setTraces(Arrays.asList(
                new Trace(TraceType.MAJOR_PASSIVE, Arrays.asList(
                        new Buff(BuffType.DAMAGE_BOOST, 72.0, 3, Target.SELF),
                        new Buff(BuffType.HP_FLAT, null, StatType.HP, null, 0.09, null, Target.SELF)
                )),
                new Trace(TraceType.MINOR_NODE, Arrays.asList(
                        new Buff(BuffType.CRIT_DMG_PERCENT, 5.3),
                        new Buff(BuffType.CRIT_DMG_PERCENT, 8.0),
                        new Buff(BuffType.HP_PERCENT, 4.0),

                        new Buff(BuffType.CRIT_RATE_PERCENT, 4.0),
                        new Buff(BuffType.CRIT_DMG_PERCENT, 8.0),

                        new Buff(BuffType.CRIT_RATE_PERCENT, 2.7),
                        new Buff(BuffType.CRIT_DMG_PERCENT, 5.3),

                        new Buff(BuffType.HP_PERCENT, 6.0),
                        new Buff(BuffType.CRIT_RATE_PERCENT, 5.3),
                        new Buff(BuffType.CRIT_DMG_PERCENT, 10.7)
                ))
        ));

        character.setMemosprite(null);
        return character;
    }

    private static Character getSunday() {
        Character character = new Character();
        character.setName("선데이");
        character.setLevel(80);
        character.setBaseHp(2406);
        character.setBaseAtk(1116);
        character.setBaseDef(1062);
        character.setSpd(96);
        character.setElement(Element.IMAGINARY);
        character.setFatePath(FatePath.HARMONY);
        character.setScalingAttribute(StatType.ATK);

        // 일반 공격
        BasicAttack basicAttack = new BasicAttack();
        basicAttack.setCharacter(character);
        basicAttack.setDamageInfo(new DamageInfo(1.0));
        character.setBasicAttack(basicAttack);

        // 전투 스킬
        Skill skill = new Skill();
        skill.setCharacter(character);
        skill.setEffectType(EffectType.SUPPORT);
        skill.setBuffs(List.of(new Buff(BuffType.DAMAGE_BOOST, 30.0, Target.SELF)));
        character.setSkill(new Skill());

        // 필살기
        Ultimate ultimate = new Ultimate();
        ultimate.setCharacter(character);
        ultimate.setEffectType(EffectType.SUPPORT);
        ultimate.setBuffs(List.of(new Buff(BuffType.CRIT_DMG_PERCENT, 0.12, StatType.CRIT_DMG, null, 0.3, null, Target.ALLY)));
        character.setUltimate(new Ultimate());

        // 특성
        Talent talent = new Talent();
        talent.setCharacter(character);
        talent.setEffectType(EffectType.SUPPORT);
        talent.setBuffs(List.of(new Buff(BuffType.CRIT_RATE_PERCENT, 0.2)));
        character.setTalent(new Talent());

        character.setTraces(List.of(
                new Trace(TraceType.MINOR_NODE, Arrays.asList(
                        new Buff(BuffType.CRIT_DMG_PERCENT, 5.3),
                        new Buff(BuffType.CRIT_DMG_PERCENT, 8.0),
                        new Buff(BuffType.DEF_PERCENT, 5.0),

                        new Buff(BuffType.EFFECT_RES_PERCENT, 4.0),
                        new Buff(BuffType.CRIT_DMG_PERCENT, 5.3),

                        new Buff(BuffType.EFFECT_RES_PERCENT, 6.0),
                        new Buff(BuffType.CRIT_DMG_PERCENT, 8.0),

                        new Buff(BuffType.DEF_PERCENT, 7.5),
                        new Buff(BuffType.CRIT_DMG_PERCENT, 10.7),
                        new Buff(BuffType.EFFECT_RES_PERCENT, 8.0)
                ))
        ));

        character.setMemosprite(null);
        return character;
    }

    private static Character getCipher() {
        Character character = new Character();
        character.setName("사이퍼");
        character.setLevel(80);
        character.setBaseHp(1883);
        character.setBaseAtk(1222);
        character.setBaseDef(1038);
        character.setSpd(106);
        character.setElement(Element.QUANTUM);
        character.setFatePath(FatePath.NIHILITY);
        character.setScalingAttribute(StatType.ATK);

        // 일반 공격
        BasicAttack basicAttack = new BasicAttack();
        basicAttack.setCharacter(character);
        basicAttack.setDamageInfo(new DamageInfo(1.0));
        character.setBasicAttack(basicAttack);

        // 전투 스킬
        Skill skill = new Skill();
        skill.setCharacter(character);
        skill.setEffectType(EffectType.HYBRID);
        skill.setDamageInfo(new DamageInfo(2.0));
        skill.setBuffs(List.of(new Buff(BuffType.ATK_PERCENT, 30.0, Target.SELF)));
        character.setSkill(new Skill());

        // 필살기
        Ultimate ultimate = new Ultimate();
        ultimate.setCharacter(character);
        ultimate.setEffectType(EffectType.DAMAGE);
        ultimate.setDamageInfo(new DamageInfo(1.2, 0.4));
        character.setUltimate(new Ultimate());

        // 특성
        Talent talent = new Talent();
        talent.setCharacter(character);
        talent.setEffectType(EffectType.DAMAGE);
        talent.setDamageInfo(new DamageInfo(1.5));
        character.setTalent(new Talent());

        // 행적
        character.setTraces(Arrays.asList(
                new Trace(TraceType.MAJOR_PASSIVE, List.of(
                        new Buff(BuffType.DAMAGE_TAKEN_INCREASE, 40.0)
                )),
                new Trace(TraceType.MINOR_NODE, Arrays.asList(
                        new Buff(BuffType.SPD_FLAT, 2.0),
                        new Buff(BuffType.SPD_FLAT, 4.0),

                        new Buff(BuffType.SPD_FLAT, 3.0),
                        new Buff(BuffType.QUANTUM_DAMAGE_BOOST, 4.8),
                        new Buff(BuffType.SPD_FLAT, 3.0),

                        new Buff(BuffType.EFFECT_HIT_PERCENT, 4.0),
                        new Buff(BuffType.SPD_FLAT, 2.0),

                        new Buff(BuffType.QUANTUM_DAMAGE_BOOST, 3.2),
                        new Buff(BuffType.EFFECT_HIT_PERCENT, 6.0),
                        new Buff(BuffType.QUANTUM_DAMAGE_BOOST, 6.4)
                ))
        ));

        character.setMemosprite(null);
        return character;
    }

}
