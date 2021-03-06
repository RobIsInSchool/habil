package com.radams.entity;

/**
 * The type Skill.
 */
public class Skill {

    private int skillId;
    private String skillName;

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
