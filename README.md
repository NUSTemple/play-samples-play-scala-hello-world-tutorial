# Play Framework Tutorial - Overview

## Goal of the tutorial

Recently I want to use scala to build a web backend. After doing some google research, [Play Framework](https://www.playframework.com/documentation/2.7.x/ScalaHome) is the choice I finally made to try. Compared to Python based framework like `Django` or `Flask`, Play is chosen due to potential business need to interact with Hadoop system. It will be easier to interact using Scala/Java compared to Python. 

As [Play Framework](https://www.playframework.com/documentation/2.7.x/ScalaHome) is the full stack web framework, we will focus more on the backend development first. Template engine used in Play Framework to generate HTML will be only mentioned in the necessary part. 

As the major goal of this tutorial is to create API using [Play Framework](https://www.playframework.com/documentation/2.7.x/ScalaHome) and interact with database like Mysql, the well-known plugin  [Slick](http://scala-slick.org/docs/) will be used also. 

## Why I write this note?
During the exploration of Play Framework, I tried to follow Play Framework [Hello World Tutorial](https://www.playframework.com/documentation/2.7.x/HelloWorldTutorial). However, a few problems are identified after trying the tutorial. 

1. Hello World Tutorial is too simple and no much more help to further understand Play
2. No clear path to explain how should I do for next step for my use case.
3. Online 3rd party doc are either outdated or too complicated


## Overall notes setup
For this series of notes, I will follow below sequence to complete the tutorial:
1. Setup a Hello-World project in Play Framework (Part 1)
2. Mysql setup and Introduction to Slick (Part 2)
3. Try Play-Slick in Play Framework to query information from Mysql (Part 3)
4. Advanced version of Slick to perform the join between tables (Part 4)


## References:
[Play Framework](https://www.playframework.com/documentation/2.7.x/ScalaHome)
[Slick](http://scala-slick.org/docs/)
