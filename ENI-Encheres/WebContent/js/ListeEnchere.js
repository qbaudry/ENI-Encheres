$( document ).ready(function() {
	$('#inputGroupSelect').change(function(){
//		$.ajax({
//			type: 'POST',
//			url: "/ENI-Encheres/Ajax_ListeEnchere",
//			data: {
//				categ : $(this).val()
//			},
//			success: function( result ) {
//				$( "#listeArticle" ).html( result );
//			}
//		});
		$.post( "/ENI-Encheres/Ajax_ListeEnchere",{ categ : $('#inputGroupSelect').val()})
		  .done(function( data ) {
			  $( "#listeArticle" ).html( data );
			  console.log(data);
			});
	});
});