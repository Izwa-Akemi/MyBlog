$(function() {
	$(".menu-toggle_btn").on('click', function() {
		$(this).toggleClass('active');
		$('.menu-inner').toggleClass('active');
		$('menu__item').toggleClass('active');
	});

	$('.search').on('click', function() {
		var categoryId = $('#categoryId').val();
		var itemName = $('#itemName').val();

		$.ajax({
			url: '/searchitem',
			dataType: "text",
			type: "GET",
			data: {
				categoryId: categoryId,
				itemName: itemName
			},
			// Ajaxが正常終了した場合
		}).done(function(data, textStatus, jqXHR) {
			// 該当するデータが無かった場合
			if (!data) {
				alert("該当するデータはありませんでした");
				return;
			}

			// 該当するデータがあった場合は、取得したUserDataオブジェクトのリストを
			// 画面のtableタグに表示
			// その際、名前・性別・メモはデコードする
			const itemList = JSON.parse(data);
			var ins = "";
			$("#products").empty();
			for (i = 0; i < itemList.length; i++) {
				ins += '<li>';
				ins += '<a href="/detail/' + itemList[i].itemId + '">';
				//ins += '<img src="./itemImage/eraser.png" alt="">';
				ins += '<img src="./itemImage/' + itemList[i].image + '">';
				ins += '<p>';
				ins += itemList[i].itemName;
				ins += '</p>';
				ins += '</a>';
				ins += '</li>';
			}
			$("#products").html(ins);
			// Ajaxが異常終了した場合
		}).fail(function(jqXHR, textStatus, errorThrown) {
			alert("エラーが発生し、データが取得できませんでした");
		});
	});
	$('#search').on('click', function() {
		var categoryId = $('#categoryId').val();
		var itemName = $('#itemName').val();

		$.ajax({
			url: '/searchadminitem',
			dataType: "text",
			type: "GET",
			data: {
				categoryId: categoryId,
				itemName: itemName
			},
			// Ajaxが正常終了した場合
		}).done(function(data, textStatus, jqXHR) {
			// 該当するデータが無かった場合
			if (!data) {
				alert("該当するデータはありませんでした");
				return;
			}

			// 該当するデータがあった場合は、取得したUserDataオブジェクトのリストを
			// 画面のtableタグに表示
			// その際、名前・性別・メモはデコードする
			const itemList = JSON.parse(data);
			var ins = "";
			$("#products").empty();
			for (i = 0; i < itemList.length; i++) {
				ins += '<li>';
				ins += '<a href="/admin/detail/' + itemList[i].itemId + '">';
				ins += '<img src="./itemImage/' + itemList[i].image + '">';
				ins += '<p>';
				ins += itemList[i].itemName;
				ins += '</p>';
				ins += '</a>';
				ins += '</li>';
			}
			$("#products").html(ins);
			// Ajaxが異常終了した場合
		}).fail(function(jqXHR, textStatus, errorThrown) {
			alert("エラーが発生し、データが取得できませんでした");
		});
	});




	/*お気に入り処理 */
	$('.add-favorite').on('click', function() {
		console.log("test");
		var index = $('.add-favorite').index(this);
		console.log(index);
		var itemId = $('.itemfavorite').eq(index).val();
		console.log(itemId);
		$.ajax({
			url: '/addfavorite',
			dataType: "text",
			type: "GET",
			data: {
				itemId: itemId
			},
			// Ajaxが正常終了した場合
		}).done(function(data, textStatus, jqXHR) {
			// 該当するデータが無かった場合
			if (!data) {
				alert("該当するデータはありませんでした");
				return;
			}
			var flg = JSON.parse(data);

			var ins = "";
			if (flg == 1) {
				console.log("red")
				ins += '<button class="add-favorite"　th:if="${items.bookmarkId != null}">';
				ins += '<img src="./favorite/heart.jpeg">';
				ins += '</button>';
				$(".add-favorite").eq(index).replaceWith(ins);
			} else{
					ins += '<button class="no-add-favorite"　th:if="${items.bookmarkId == null}">';
					ins += '<img src="./favorite/noheart.png">';
				ins += '</button>';
				$(".add-favorite").eq(index).replaceWith(ins);
			}


			// Ajaxが異常終了した場合
		}).fail(function(jqXHR, textStatus, errorThrown) {
			alert("エラーが発生し、データが取得できませんでした");
		});
	});
	

});