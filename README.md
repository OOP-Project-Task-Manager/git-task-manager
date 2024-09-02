# Task Management Console Application

## Overview
A console application designed for a small team of developers to manage tasks related to their software project. The application supports multiple teams, boards, and task types, ensuring effective task tracking and collaboration.

## Features

### Teams
- Create and manage teams: Unique name (5-15 characters), members, and boards.
- Team members: Each has a unique name, tasks, and activity history.

### Boards
- Create and manage boards: Unique name (5-10 characters) within the team.
- Boards: Maintain a list of tasks and an activity history.

### Task Types
Supports three types of tasks:
- **Bug**: Title, description, steps to reproduce, priority, severity, status, assignee, comments, and history.
- **Story**: Title, description, priority, size, status, assignee, comments, and history.
- **Feedback**: Title, description, rating, status, comments, and history.

### Operations
- Manage teams: Create teams, add members, view team details.
- Manage boards: Create boards, view board details.
- Manage tasks: Create tasks, change status, assign/unassign tasks, add comments.
- List and filter tasks: By type, status, assignee, title, etc.

## Setup and Usage
1. Clone the repository:
    ```sh
    git clone https://github.com/username/repository.git
    ```
2. Build the project:
    ```sh
    mvn clean install
    ```
3. Run the console application:
    ```sh
    java -jar target/task-management-console-app.jar
    ```
4. Follow the prompts to manage teams, boards, and tasks.
