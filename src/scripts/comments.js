var comments = new Array(); // global variable for all comments
var likes = new Array() // global parallel array for all likes 

presetComment("0xCA2", "This episode was awesome!");
presetComment("Antroid", "Try these comments. They work.");

function processComment() {
	if (emptyName()) {
		alert("Please enter a name.");
	} else if (emptyComment()) {
		alert("Please enter a comment.");
	} else {
		comments.push(getComment());
		
		var commentString = "";
		commentString = alignComments(comments, commentString);
		document.getElementById("comments").innerHTML = commentString;
		box.value = "";
		document.getElementById("uName").value = "";
		
		setNewestFirstBox();
	}
}

function getComment() {
	var name = document.getElementById("uName").value;
	var text = box.value;
	var userLikes = getLikes();
	likes.push(userLikes);
	return makeComment(name, getAvatar(), text, userLikes);
}

function presetComment(name, text) {
	var userLikes = getLikes();
	likes.push(userLikes);
	comments.push(makeComment(name, getAvatar(), text, userLikes));
	var commentString = "";
	commentString = alignComments(comments, commentString);
	document.getElementById("comments").innerHTML = commentString;
}


// display comments in order of newest comment first 
function alignComments(comments, commentString) {
	for (var i = 0; i < comments.length; i++) {
		commentString += comments[comments.length-i - 1];
	}
	
	return commentString;
}

function emptyName() {
	if (document.getElementById("uName").value == "") {
		return true;
	} else {
		return false;
	}
}

function emptyComment() {
	if (box.value == "") {
		return true;
	} else {
		return false;
	}
}
/* Returns time as a H:MM string */

function getTime() {
	var date = new Date();
	var time = "";
	time += formatTime(date);
	return time;
}

function formatTime(date) {
	var h = date.getHours();
	var m = date.getMinutes();
	
	m = formatUnit(m);
	
	if (h > 12) {
		h = h - 12;
		h - formatUnit(h);
		return h + ":" + m + " PM";
	} else {
		return h + ":" + m + " AM";
	}
}

function formatUnit(n) {
	if (n < 10) {
		return  "0" + n;
	} else {
		return n;
	}
}

function makeComment(user, avatar, text, userLikes) {
	var comment = "";
	comment += "<div id='comment'>";
	comment += "<div id='avatar_holder'>";
	comment += "<img width='60' height='60' src='" + avatar + "' alt='avatar'>";
	comment += "</div>";
	comment += "<div id='comment_content'>";
	comment += "<div id='comment_header'>";
	comment += "<span id='user_name'>" + user + "</span>";
	comment += "<span id='comment_time'>" + getTime() + "</span>";
	comment += "</div>";
	comment += "<div id='comment_text'>";
	comment += text + "</div>";
	comment += "<div id='comment_likes'>";
	comment += "<label id='likes_label'>Likes:" +"</label>";
	comment += userLikes + "</div>";
	comment += "</div>";
	comment += "</div>";
	
	return comment;
}

/*Returns a number between 0 and 1000*/
function getLikes() {
	return Math.floor(Math.random() * 1001);
}

/* Returns a number between 1 and 52 for the avatars */
function getAvatar() {
	var pictureNumber = Math.floor(Math.random() * 52) + 1;
	return pictureNumber + ".jpg";
}


/* using insertion sort algorithm*/ 
function sortByLikes(comments, likes) {
	var sortedComments = comments.slice(0); 
	var sortedLikes = likes.slice(0);
	for (var i = 0; i < comments.length; i++) {
		var k = sortedLikes[i];
		var e = sortedComments[i];
		for (var j = i; j > 0 && k > sortedLikes[j - 1]; j--){
			sortedLikes[j] = sortedLikes[j - 1];
			sortedComments[j] = sortedComments[j - 1];
		}
		sortedComments[j] = e;
		sortedLikes[j] = k;
	} 
	
	return sortedComments;
}

/* Just makes it show newest first upon a new comment */
function setNewestFirstBox() {
	var commentSort = document.getElementById("comment_sort");
	var optionArray = commentSort.options;
	optionArray[0].selected = true;
}

function setTopSort() {
	var sortedComments = sortByLikes(comments, likes);
	document.getElementById("comments").innerHTML = sortedComments.join("");;
}

function setNewestSort() {
	var commentString = "";
	console.log(comments);
	commentString =  alignComments(comments, commentString);
	console.log(comments);
	document.getElementById("comments").innerHTML = commentString;
}

function setSort() {
	var commentSort = document.getElementById("comment_sort");
	var optionArray = commentSort.options;
	var commentString = "";
	
	if (optionArray[0].selected) {
		setNewestSort();
	} else if (optionArray[1].selected) {
		setTopSort();
	}
}


