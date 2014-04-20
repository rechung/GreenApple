function wishList() {

	var wishListUrl = "wishList" + "?teacherEmail="
			+ encodeURIComponent(getUrlParameter("teacherEmail"));

	$.post(wishListUrl, {}, function(data, status) {

		var wishList = jQuery.parseJSON(data);

		itemsList = "<fieldset data-role='controlgroup'>"
				+ "<legend>WISH LIST:</legend>";

		$.each(wishList, function(key, val) {

			itemsList += "<input type='checkbox' name='checkbox-0'"
					+ " checked value='"
					+ decimalRound(val.itemQuantity * val.itemUnitPrice, 2)
					+ "' />" + val.itemQuantity + " <a href='" + val.itemLink
					+ "' target='_blank'>" + val.itemName + "</a> - US$ "
					+ decimalRound(val.itemQuantity * val.itemUnitPrice, 2)
					+ "<br>";

		});

		itemsList += "</fieldset><button class='ui-btn ui-corner-all' "
				+ "onclick='buyitNow()'>Buy Items Now</button>";

		panel_WishList.innerHTML = itemsList;

	});
}

function buyitNow() {
	$("#panel_WishList").hide();
	$("#panel_BuyItNow").load('screens/buyItNow.html');
	$("#panel_BuyItNow").show();
}

$(document).on(
		"pageinit",
		function(event) {

			$("#panel_WishList").hide();
			$("#panel_WishListOtherTeachers").hide();
			$("#panel_BuyItNow").hide();
			$("#panel_wishListForm").hide();
			$("#panel_WishListOtherTeachers")
					.load('screens/wishListOtherTeachers.html');

			if (getUrlParameter('teacher') != null
					&& getUrlParameter('teacher') != '') {
				$("#panel_header").html(
						'<label>Teacher: ' + getUrlParameter('teacher')
								+ '</label>');
			} else {
				$("#panel_header").html(
						'<input id="filter-for-listview" data-type="search"'
								+ 'placeholder="Search for Teachers..." />');
				$("#panel_WishListOtherTeachers").show();
			}

			if (getUrlParameter('form') == 'true') {
				$("#panel_wishListForm").load('screens/wishListForm.html');
				$("#panel_wishListForm").show();
			} else {

				if (getUrlParameter('teacher') != null
						&& getUrlParameter('teacher') != '') {

					$("#panel_WishList").show();
					wishList();
				}
				$("#panel_WishListOtherTeachers").show();
			}
		});
