package com.yvonne.portfolio.model;

import java.util.List;

public record PortfolioProfile(
        String fullName,
        String title,
        String location,
        String phone,
        String email,
        String summary,
        List<String> highlights,
        List<SkillCategory> skillCategories,
        List<Education> education,
        List<Experience> experience,
        List<Certificate> certificates
) {
}
