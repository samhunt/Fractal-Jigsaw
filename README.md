Fractal-Jigsaw
==============

Draws a fractal jigsaw according to the order inputted by the user.

Fractal
-------

An order of zero would look like

          _______
         |       |
         |       |
         |_______|
        
Where as an order of one would look like

           _   _
         _| |_| |_
        |_   _   _|
          |_| |_|

As

* Top side

        ____ becomes _   _
                      |_|

* Right side

        |           |_
        |  becomes   _|
        |           |

* Bottom side

                       _
        ____ becomes _| |_
        
* Left side


        |            _|
        |  becomes  |_
        |             |

Usage
-----

```java
// The order of fractal that you want to get.
int order = 0;
// Create fractal object. (Either iterative or recursive)
// Fractal class is encapsulated so you could change the way of working out
// what the fractal looks like between iterative and recursive
// without the user knowing.
// Fractal<Iterative|Recursive> fractal = new Fractal<Iterative|Recursive>(order);
// ie
FractalRecursive fractal = new FractalRecursive(order);
// or
FractalIterative fractal = new FractalIterative(order);
length = fractal.getLength();
ArrayList<char> frac = fractal.getFractalJigsaw(order);
//then draw fractal jigsaw...
```
