# <h1 align="center"><b>Hit The Android</b></h1>
<p align="center">
   <img src="https://media.discordapp.net/attachments/776491759358181380/849358297421316146/2021-06-01_20_45_16-Hit_the_Android.jpg" alt="Preview" height="390">
</p>

## About the Project
The project has been carried out by <a href = "https://github.com/Parmo7"> Parminder Saini </a>, as part of the second-year module "Computer Graphics", using OpenGL. Please check file ["demo.mp4"](demo.mp4) for a brief demonstrational video

### The Task
The task is to use the OpenGL graphics library in Java in order to create a scene with some simple 3D animations and at least one advanced animation around game interfaces. 
The scene should contain some animation which is interactive, i.e. the user can affect what happens in the scene.


### Overview
The game interface consists of 4 Androids located in a futuristic land, with neon graphics and pyramids
moving around. Androids will pop up from the ground for a limited time in random order. The goal for
the player is to hit them (using number keys) as quickly as possible before they get back to their safe
shelter beneath the ground! If the attempt is successful, the score increases. Otherwise, the scene
temporarily becomes red and the score is reduced. While an Android is shown on the screen, it is possible
to make it spin around its axis as well as change the direction/speed using the designated key(s).

### User Interaction
If an Android is active (i.e. rising or staying on the screen):
<ul>
<li>Press the R key to make the Android spin around its axis</li>
<li>While spinning, press the LEFT or RIGHT keys to change the direction and/or speed of rotation</li>
<li>Press the S key to stop the spinning animation</li>
<li>Press the number key associated with the Android (1, 2, 3 or 4) to hit it.</li>
</ul>
The number key of an Android is given by its position along the y axis

### Animations
Scripted:
<ul>
<li>Rotation: two pyramids are continuously moving on the ground plane.</li>
<li>Translation: Androids translate along the Y axis to rise from the ground / sink in the ground.</li>
<li>Rotation: the arms of Androids move up and down relative to the Android.</li>
</ul>
User Controlled:
<ul>
<li>Rotation: when R is pressed, the active Android starts spinning.</li>
<li>Translation: when an Android is hit using the appropriate key, it falls back into the ground.</li>
</ul>

## Lightning
The scene has a dim global ambient contribution and a single light (LIGHT_0) placed above and to the
right of the camera. Androids react to lighting according to their material properties, and different
components of these robots react differently (e.g. body, hands, eyes). The overall effect is 4 Androids
rendered in different colours (blue, yellow, red and green).

