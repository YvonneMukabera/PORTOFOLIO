package com.yvonne.portfolio.model;

import java.util.List;

public record Experience(String role, String organization, String period, List<String> responsibilities) {
}
