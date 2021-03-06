package com.radams.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Skill.
 */
@Entity(name = "Skill")
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private int skillId;

    @Column(name = "skill_name")
    private String skillName;

    @ManyToMany
    @JoinTable(
            name = "user_skills_has",
            joinColumns = { @JoinColumn(name = "id") },
            inverseJoinColumns = { @JoinColumn(name = "id") }
    )
    private Set<User> usersHave = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_skills_wants",
            joinColumns = { @JoinColumn(name = "id") },
            inverseJoinColumns = { @JoinColumn(name = "id") }
    )
    private Set<User> usersWant = new HashSet<>();

    /**
     * Instantiates a new Skill.
     */
    public Skill() {
    }

    /**
     * Instantiates a new Skill.
     *
     * @param skillId   the skill id
     * @param skillName the skill name
     */
    public Skill(int skillId, String skillName) {
        this();
        this.skillName = skillName;
    }

    /**
     * Gets skill id.
     *
     * @return the skill id
     */
    public int getSkillId() {
        return skillId;
    }

    /**
     * Sets skill id.
     *
     * @param skillId the skill id
     */
    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    /**
     * Gets skill name.
     *
     * @return the skill name
     */
    public String getSkillName() {
        return skillName;
    }

    /**
     * Sets skill name.
     *
     * @param skillName the skill name
     */
    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "skillId=" + skillId +
                ", skillName='" + skillName + '\'' +
                '}';
    }
}
