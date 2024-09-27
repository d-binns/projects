// Your solution goes here 

function isStrongPassword(word){
	if (word.length < 8) {
		return false;
	}
	if (word.indexOf("password") != -1 ){
		return false;
	}
	
	for (i = 0; i < word.length; i++);{
		if ((word.charCodeAt(word[i]) >= 65) || (word.charCodeAt(word[i]) <=90)){
			return true;
		}
	}
	return false;
}