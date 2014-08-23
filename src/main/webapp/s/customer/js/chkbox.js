/*
 * input[type='checkbox'] : init.
 */
function initChkbox(id, chkedVal) {
	if ($('#' + id).val() == chkedVal) {
		$('#' + id).attr('checked', true);
	} else {
		$('#' + id).attr('checked', false);
	}
}

/*
 * input[type='checkbox'] : onclick.
 */
function chkboxOnclick(id, chkedVal, unchkedVal) {
	if ($('#' + id).attr('checked')) {
		$('#' + id).attr('checked', false);
		$('#' + id).val(unchkedVal);
	} else {
		$('#' + id).attr('checked', true);
		$('#' + id).val(chkedVal);
	}
}