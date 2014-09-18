/* header.js
 * This program simply inserts the header of the page 
 * in the browser. Since JavaScript doesn't allow for
 * multi-line strings, it must be done this way or 
 * similar ways.
 */

var h = "";
h += "<div id='hwrapper'> ";
h += "<div id='main' class='navigation'>";
h += "<a href='index.html'>";
h += "<img id='hlogo' src='hthg_small2.png' alt='site logo'/>";
h += "</a>";  
h += "<div id='selecton_bar'>" ;
h += "<nav class='navigation'>";
h += "<a class='navigation' href='index.html'>";
h += "HOME";
h += "</a>";
h += "<a class='navigation' href='faq.html'>";
h += "FAQ";
h += "</a>";
h += "<a class='navigation' href='about.html'>";
h += "ABOUT";
h += "</a>";
h += "</nav>";
h += "</div>";
h += "</div>";
h += "</div>";
h += "<div id='category_wrapper'>";
h += "<nav class='category_bar'>";
h +=  "<a href='family.html'>Family</a> / ";
h += "<a href='school.html'>School</a> / ";
h += "<a href='arc.html'>Arc</a> / ";
h += "<a href='bullying.html'>Bullying</a> / ";
h += "<a href='origin.html'>Origin</a> / ";
h += "<a href='morbid.html'>Morbid</a> / ";
h += "<a href='unrequited_love.html'>Unrequited Love</a> / ";
h += "<a href='slice_of_life.html'>Slice of Life</a> / ";
h += "<a href='adolescence.html'>Adolescence</a> / ";
h += "<a href='abuse.html'>Abuse</a> / ";
h += "<a href='crime.html'>Crime</a> / ";
h += "<a href='neighbor.html'>Neighbor</a> / ";
h += "<a href='accident.html'>Accident</a>   ";
h += "<a href='politics.html'>Politics</a> / ";
h += "<a href='work.html'>Work</a> / ";
h += "<a href='friendship.html'>Friendship</a>";
h += "</nav>";
h += "</div>";

document.writeln(h);


