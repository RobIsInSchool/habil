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

    @ManyToMany(mappedBy = "skillsHas", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> usersHave = new HashSet<>();

    @ManyToMany(mappedBy = "skillsWants", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> usersWant = new HashSet<>();

    /**
     * Instantiates a new Skill.
     */
    public Skill() {
    }

    /**
     * Instantiates a new Skill.
     *
     * @param skillName the skill name
     */
    public Skill(String skillName) {
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

    public void addUsersHas(User user) {
        usersHave.add(user);
    }

    public void removeUsersHas(User user) {
        usersHave.remove(user);
    }

    public void addUsersWants(User user) {
        usersWant.add(user);
    }

    @Override
    public String toString() {
        return "Skill{" +
                "skillId=" + skillId +
                ", skillName='" + skillName + '\'' +
                '}';
    }
}
