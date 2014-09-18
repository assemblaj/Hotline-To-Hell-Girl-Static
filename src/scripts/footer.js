/* footer.js
 * This program simply inserts the footer of the page 
 * in the browser. Since JavaScript doesn't allow for
 * multi-line strings, it must be done this way or 
 * similar ways.
 */


var f = "";
var d = new Date();
f += "<div id='fwrapper'>";
f += "<div class='footer'>";
f += "Copyright &copy;" + d.getFullYear() + " Hotline to Hell Girl ";
f += "</div>";
f += "</div>";

document.writeln(f);

