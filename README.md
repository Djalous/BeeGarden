# Designing a Garden

## Overview

In this simulator, bees visit flowers for food and flowers can drain energy. The garden will be shown from above with
bees moving through the flower bed.

### Bees

* Bees move a (???) distance each tick.
* Euclidean distance \sqrt{(x_1 - x_2)^2 + (y_1 - y_2)^2} between the (approximate) centers of objects is used to
determine when a bee hits a flower.
* Each bee is has an energy level.
    * The bee loses one or more energy points on each tick and dies if the energy level reaches zero.
    * The energy levels and amounts lost on each tick are balanced so that bees live indefinitely when there are a
    sufficient number of flowers but die from starvation when there are not enough flowers.
* The user is able to adjust the number of bees and flowers in the garden so as to test different population mixes.
    * When there are very few flowers relative to the bees, the bees should die quickly.
    * When there are many flowers, the bees should live a long time.
    * At the very least there is support for up to dozens of bees and dozens of flowers of different types.
* Include two types of bees.
    * One of the bees picks a flower at random and then moves towards that flower. The bee will then move towards the
    flower until it reaches it, then a new flower is picked.
    * The other bee has a different pattern such as flying in straight lines back and forth (covering the full garden)
    or moving in circles.
    * Each bee has a simple, distinct graphic along with a graphical and numerical display of its energy level.
        * The energy level is displayed in two ways: as a number and as a health bar.
        * The health information is on the screen near to its bee and moves with the bee.

### Flowerbed

*  Includes two types of flowers: one that provides a given number of units of energy (in the form of nectar)
and one that drains energy from visitors.
* The behavior of both the one that provides energy and the one that drains it changes from visitor to visitor.
    * ???
* Flowers have simple, distinct graphics.
    * Like the bees, each must have an indicator of its status next to it.

### Key

* On the primary window (scene),  a box that displays a key showing the types of flowers and bees (graphically) and
briefly explaining the behavior of each is included.
