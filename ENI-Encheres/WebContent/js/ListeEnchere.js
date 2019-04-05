$( document ).ready(function() {
	$('#inputGroupSelect').change(function(){
		filtrer();		
	});
	$('#filtre').on('keyup paste',function(){
		filtrer();		
	});
});

function filtrer(){
	$.post( "/ENI-Encheres/Ajax_ListeEnchere",{ categ : $('#inputGroupSelect').val(),filtre : $('#filtre').val()})
	  .done(function( data ) {
		  $( "#listeArticle" ).html( data );
		});
}