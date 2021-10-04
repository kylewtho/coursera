/*Name this external file gallery.js*/

function upDate(previewPic){
 /* In this function you should 
    1) change the url for the background image of the div with the id = "image" 
    to the source file of the preview image
    
    2) Change the text  of the div with the id = "image" 
    to the alt text of the preview image 
    */
  oldText = document.getElementById("image").innerHTML; //save the original text as oldText
  document.getElementById("image").innerHTML = previewPic.alt; //replace the text
  document.getElementById("image").style.backgroundImage = "url(" + previewPic.src + ")"; //replace the background image
	}

	function unDo(){
     /* In this function you should 
    1) Update the url for the background image of the div with the id = "image" 
    back to the orginal-image.  You can use the css code to see what that original URL was
    
    2) Change the text  of the div with the id = "image" 
    back to the original text.  You can use the html code to see what that original text was
    */
  document.getElementById("image").innerHTML = oldText; //restore the text
  document.getElementById("image").style.backgroundImage = ""; //restore the background image
	}