function jsOver(el, blnOver) {
	el.className = el.className.replace('-over', '')
	
	if (blnOver)
		el.className += '-over'
}