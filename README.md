🎬 YouTube Clone Application

::: {align="center"}

📺 YouTube Desktop Application

A Java-based YouTube clone project that simulates core video-sharing
platform features including users, channels, videos, playlists,
comments, subscriptions, and content management.
:::

🚀 About The Project

YouTube Clone is a desktop application developed using Java with
an object-oriented design approach.

The goal of this project is to model the main concepts behind a video
streaming platform and implement relationships between users, channels,
videos, playlists, and platform management.

The project focuses on:

Object-Oriented Programming (OOP)

Software design principles

Model-View separation

Entity relationships

Application logic implementation

✨ Features

👤 User Management

User registration and authentication

Normal users and premium users

Admin management

User profiles

📺 Channel System

Create and manage channels

Upload content

Channel playlists

Channel management

🎥 Video & Content Management

Supported content types:

Normal Videos

Short Videos

Live Streams

Podcasts

Features: - Video categories - Video quality and format management -
Content information management

💬 Social Features

Comments

Reports

User interaction with content

📂 Playlist System

Create playlists

Add/remove videos

Manage channel playlists

🛠️ Technologies Used

<p align="left">

<img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" width="50"/>{=html}
<img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" width="50"/>{=html}

</p>

Java

JavaFX

Object-Oriented Programming

MVC Architecture Concepts

UML Design

🏗️ Project Architecture

The project follows a structured architecture:

src/main/java/com/example/demo3

├── Controller
│   ├── UserController
│   ├── ChannelController
│   ├── PlaylistController
│   └── AdminController
│
├── Model
│   ├── Account
│   │   ├── User
│   │   ├── Admin
│   │   ├── NormalUser
│   │   └── PremiumUser
│   │
│   ├── Content
│   │   ├── Video
│   │   ├── ShortVideo
│   │   ├── Podcast
│   │   └── LiveStream
│   │
│   ├── Channel
│   ├── Playlist
│   ├── Comment
│   └── Database
│
└── View
    └── JavaFX UI Controllers

🧩 Main Classes

Account Module

Handles different types of platform users:

User

Admin

Premium User

Normal User

Content Module

Abstract content management system:

Video

Short Video

Podcast

Live Stream

Platform Entities

Channel

Playlist

Category

Comment

Report


▶️ How To Run

Requirements

Java JDK 17+

JavaFX SDK

Steps

Clone the repository:

git clone https://github.com/MohammadMansoury84/YouTube.git

Open the project in IntelliJ IDEA.

Configure JavaFX libraries.

Run:

<!-- -->

MainPage.java

🎯 Future Improvements

Database integration

REST API backend

Cloud storage for videos

Real authentication system

Recommendation algorithm

Online streaming support

👨‍💻 Developer

Mohammad Mansouri

Backend Developer | Java | Spring Boot | .NET | Python

GitHub: https://github.com/MohammadMansoury84

⭐ If you like this project, consider giving it a star!
