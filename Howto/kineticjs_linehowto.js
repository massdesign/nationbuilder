 stage.on("mousedown", function(){
            if (moving){
                moving = false;layer.draw();
            } else {
                var mousePos = stage.getMousePosition();
                //CHANGED - Create new line
                line = new Kinetic.Line({
                    points: [0, 0, 50, 50],
                    stroke: "red"
                });
                //CHANGED - Add line to layer
                layer.add(line);
                //start point and end point are the same
                line.getPoints()[0].x = mousePos.x;
                line.getPoints()[0].y = mousePos.y;
                line.getPoints()[1].x = mousePos.x;
                line.getPoints()[1].y = mousePos.y;

                moving = true;    
                layer.drawScene();            
            }

        });