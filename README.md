# KD-Tree
Some data structure fun for algorithms!

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
![Python](https://img.shields.io/badge/python-v3-blue.svg)
[![Build Status](https://travis-ci.org/anfederico/Clairvoyant.svg?branch=master)](https://travis-ci.org/anfederico/Clairvoyant)
![Contributions welcome](https://img.shields.io/badge/contributions-welcome-orange.svg)

## Overview

Overview of project

## Required Software
The only required software is .... To install these packages, type

```java
installation command                       
```


## Introduction to KD Trees
file, the view of the world was limited to be around the main character in the environment. Discretizing the limited view produces a state that captures only 
the character, monsters, walls, bombs, and explosion cells inside the view as opposed to the view of the entire game. Shown below is a summarized process of 
simplify state extraction from the given world.

<p align="center"><img width=58% src="https://github.com/JosuContrer/Bomberman_AI/blob/master/group07/media/world_to_state.png"></p>

As seen in the Figure, the overall view of the world is cropped to just the view around the character, which is passed into a function that discretizes the 
view into a state. A cell is marked for example with a ‘w’ if there is a wall, with ‘m’ if  there is a monster, and with ‘o’ if the cell is otherwise unoccupied. 
Increasing the size of this view will allow for taking into account more possibles and thus tracing more states. This would be beneficial if computing time and 
storage did not factor into the overall concern for functional performance for the project.

### World Rewards

Reward values were assigned based on events that would occur in the next state. For Bomberman, the reward of an action taken involves the presence of monsters, 
bombs, explosion blocks, walls, and other characters. For the purpose of creating the Q-table, arbitrary values were assigned to rewards depending on the 
action-state outcome associated with the reward. The general idea for assigning rewards is to have lethally bad moves to have a large negative value and winning 
or good moves to have large positive values. The reason for this arrangement is to both encourage “good” plays and discourage “bad” plays. In our case, “good” 
plays would be defined as making progress towards the exit in the map and “bad” plays would be self-destructive actions, which include blowing oneself by walking 
into an explosion cell (appears after a placed bomb explodes) or walking into a monster (occupying the same cell results in death).

## Results

## Authors

* **Will Burke**
* **Josue Contreras**
* **Mayank Govilla**
* **Ian Coolidge**

## Acknowledgments

* Introduction to KD Trees [article](https://www.geeksforgeeks.org/k-dimensional-tree/)
