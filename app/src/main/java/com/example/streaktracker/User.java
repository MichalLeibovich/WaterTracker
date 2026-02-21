package com.example.streaktracker;


import com.google.firebase.Timestamp;

public class User
{
    private String username;
    private int streak;
    private final Timestamp createdAt;

    public User(String username, int streak, Timestamp createdAt)
    {
        this.username = username;
        this.streak = streak;
        this.createdAt = createdAt;
    }

    public String getUsername() {
        return username;
    }

    public int getStreak() {
        return streak;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

}
