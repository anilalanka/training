var c = document.getElementById("demo");
var ctx = c.getContext("2d");

c.width = window.innerWidth;
c.height = window.innerHeight;

var r =5;
var xList = [];
var yList = [];
var i = 0;
window.onload = function(){
    window.onclick =function(e){
        // clear the canvas on click
        ctx.clearRect(0,0,c.width,c.height);
        var clearance = [10,10]; // through trail & error
        var x = e.clientX - clearance[0];
        var y = e.clientY - clearance[1];

        xList[i] = x;
        yList[i] = y;
        // draw all points from beginning
        for(var j=0;j<i;j++){
            if(i>0){
                if(getDistanceSquared(xList[j],yList[j],x,y) < r*r){
                    xList[j] =null;
                    xList[i] =null;
                }
            }
            drawPoint(xList[j],yList[j],r,ctx);
        }
        drawPoint(xList[i],yList[i],r,ctx);
        var xmin =null;
        var ymin = null;
        var rmin = Number.MAX_VALUE;
        for(var m=0;m<=i-2;m++){
            if(xList[m]==null)continue;
            for(var n=m+1;n<=i-1;n++){
                if(xList[n]==null)continue;
                for(var p=n+1;p<=i;p++) {
                    if (xList[p] == null)continue;
                    var C = getCircle(xList[m],yList[m],xList[n],yList[n],xList[p],yList[p]);
                    var allInside = true;
                    for(var k=0;k<=i;k++){
                        if(k==m || k==n || k==p || xList[k]==null)continue;
                        if(getDistanceSquared(C[0],C[1],xList[k],yList[k]) > C[2]*C[2]) {allInside = false; break;}
                    }
                    if(C[2] <rmin && allInside){
                        xmin=C[0];
                        ymin=C[1];
                        rmin=C[2];
                    }
                }
            }

        }
        drawPoint(xmin,ymin,rmin,ctx);
        var str = "Centre: ["+xmin+", "+ymin+"] \nRadius: "+rmin;
        ctx.font = "16px Arial";
        ctx.textAlign = "center";
        ctx.fillText(str,c.width/2,30);

        i++;
    }
}

function drawPoint(x,y,r,ctx){	// to draw point with r <= 0.5
    if(x==null) return;
    ctx.beginPath();
    ctx.arc(x,y,0.5,0,2*Math.PI);	// Point
    ctx.stroke();

    ctx.beginPath();
    ctx.arc(x,y,r,0,2*Math.PI); // Clearance around point
    ctx.stroke();
}
function drawCircleThreePoints(x1,y1,x2,y2,x3,y3,ctx){
    if(x1==null || x2 == null || x3==null) return;
    C = getCircle(x1,y1,x2,y2,x3,y3);
    drawPoint(C[0],C[1],C[2],ctx);
}
function getCircle(x1,y1,x2,y2,x3,y3){
    if(x1==null || x2 == null || x3==null) return;
    var mr = (y2-y1)/(x2-x1);	// slope
    var mt = (y3-y2)/(x3-x2);	// slope
    var cx = (mr*mt*(y3-y1)+mr*(x2+x3)-mt*(x1+x2))/(2*(mr-mt)); // centre x
    var cy = -1/mr*(cx - (x1+x2)/2)+(y1+y2)/2;		// centre y
    var r = Math.sqrt((cx-x1)*(cx-x1)+(cy - y1)*(cy - y1)); // radius
    return [cx,cy,r];
}
function getDistanceSquared(x1,y1,x2,y2){
    if(x1== null || x2== null) return Number.MAX_VALUE;
    return Math.pow(x2-x1,2)+Math.pow(y2-y1,2);
}