$( document ).ready(function() {
	$('#inputGroupSelect').change(function(){
		filtrer();		
	});
	$('#filtre').change(function(){
		filtrer();		
	});
});

function filtrer(){
	console.log($('#filtre').val());
	$.post( "/ENI-Encheres/Ajax_ListeEnchere",{ categ : $('#inputGroupSelect').val(),filtre : $('#filtre').val()})
	  .done(function( data ) {
		  $( "#listeArticle" ).html( data );
		});
}