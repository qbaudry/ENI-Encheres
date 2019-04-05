$( document ).ready(function() {
	$('#inputGroupSelect').change(function(){
		filtrer();		
	});
	$('#filtre').on('keyup paste',function(){
		filtrer();		
	});
	filtrer();
	$('#achat').click(function(){
		$('enchere_ouverte').prop('disabled','');
		$('enchere_encours').prop('disabled','');
		$('enchere_remportees').prop('disabled','');
		$('ventes_encours').prop('disabled','disabled');
		$('ventes_non_debutees').prop('disabled','disabled');
		$('ventes_terminees').prop('disabled','disabled');
	});
	$('#achat').click(function(){
		$('enchere_ouverte').prop('disabled','disabled');
		$('enchere_encours').prop('disabled','disabled');
		$('enchere_remportees').prop('disabled','disabled');
		$('ventes_encours').prop('disabled','');
		$('ventes_non_debutees').prop('disabled','');
		$('ventes_terminees').prop('disabled','');
	});
});

function filtrer(){
	$.post( "/ENI-Encheres/Ajax_ListeEnchere",{ categ : $('#inputGroupSelect').val(),filtre : $('#filtre').val()})
	  .done(function( data ) {
		  $( "#listeArticle" ).html( data );
		});
}