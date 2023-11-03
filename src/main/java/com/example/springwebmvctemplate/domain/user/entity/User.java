package com.example.springwebmvctemplate.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USERS")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;
  private String name;
  private String profileFileName;

  @Builder
  public User(String email, String name, String profileFileName) {
    this.email = email;
    this.name = name;
    this.profileFileName = profileFileName;
  }

  public void updateProfile(String profileFileName) {
    this.profileFileName = profileFileName;
  }
}
