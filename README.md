aslettemark's ten.java submission
==============================

[![ten.java](https://cdn.mediacru.sh/hu4CJqRD7AiB.svg)](https://tenjava.com/)

This is a submission for the 2014 ten.java contest.

- __Theme:__ How can energy be harnessed and used in the Minecraft world?
- __Time:__ Time 2 (7/12/2014 09:00 to 7/12/2014 19:00 UTC)
- __MC Version:__ 1.7.9 (latest Bukkit beta)
- __Stream URL:__ None

<!-- put chosen theme above -->

---------------------------------------

Compilation
-----------

- Download & Install [Maven 3](http://maven.apache.org/download.html)
- Clone the repository: `git clone https://github.com/tenjava/aslettemark-t2`
- Compile and create the plugin package using Maven: `mvn`

Maven will download all required dependencies and build a ready-for-use plugin package!

---------------------------------------

Usage
-----

1. Install plugin

The plugin let's you gain and stock up on divine energy, by sacrificing(dropping) valuable items. To be in sacrifice mode type /drop.
With this newly acquired energy you can buy certain special effects. These effects include cool visuals, alchemy and other... things!
Buy effects by typing /effect <name of effect> <on or buy> [amount, default=1], ex. /effect alchemy buy 2.
Effects are reset on disconnect, however energy points are not. You may discard(and trash) any active effects by either
a) Typing /effect remove
b) Overriding the effect by buying a new one

<!-- Hi, aslettemark! This is the default README for every ten.java submission. -->
<!-- We encourage you to edit this README with some information about your submission – keep in mind you'll be scored on documentation! -->
