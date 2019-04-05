$( document ).ready(function() {
	$('#inputGroupSelect').change(function(){
		filtrer();		
	});
	$('#filtre').on('keyup paste',function(){
		filtrer();		
	});
	filtrer();
	$('#achat_btn').click(function(){
		$('#encheres_ouvertes').prop('disabled','');
		$('#encheres_encours').prop('disabled','');
		$('#encheres_remportees').prop('disabled','');
		$('#ventes_encours').prop('disabled','disabled');
		$('#ventes_encours').prop('checked',false);
		$('#ventes_non_debutees').prop('disabled','disabled');
		$('#ventes_non_debutees').prop('checked',false);
		$('#ventes_terminees').prop('disabled','disabled');
		$('#ventes_terminees').prop('checked',false);
	});
	$('#vente_btn').click(function(){
		$('#encheres_ouvertes').prop('disabled','disabled');
		$('#encheres_ouvertes').prop('checked',false);
		$('#encheres_encours').prop('disabled','disabled');
		$('#encheres_encours').prop('checked',false);
		$('#encheres_remportees').prop('disabled','disabled');
		$('#encheres_remportees').prop('checked',false);
		$('#ventes_encours').prop('disabled','');
		$('#ventes_non_debutees').prop('disabled','');
		$('#ventes_terminees').prop('disabled','');
	});
});

function filtrer(){
	$.post( "/ENI-Encheres/Ajax_ListeEnchere",{ categ : $('#inputGroupSelect').val(),filtre : $('#filtre').val()})
	  .done(function( data ) {
		  $( "#listeArticle" ).html( data );
		});
}