package cat.udl.eps.fragments.ejmoreflexible;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import android.view.Menu;

import cat.udl.eps.fragments.ejmoreflexible.FragmentListado.CorreosListener;

;

public class MainActivity extends FragmentActivity implements CorreosListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		FragmentListado frgListado
		      =(FragmentListado)getSupportFragmentManager()
				.findFragmentById(R.id.FrgListado);
		
		frgListado.setCorreosListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public void onCorreoSeleccionado(Correo c) {
		FragmentDetalle fgdet = (FragmentDetalle) getSupportFragmentManager().findFragmentById(R.id.FrgDetalle);
		boolean hayDetalle = (fgdet != null && fgdet.isInLayout());
		
		if (hayDetalle) {
			fgdet.mostrarDetalle(c.getTexto());
		}
		else {
			Intent i = new Intent(this, DetalleActivity.class);
			i.putExtra(DetalleActivity.EXTRA_TEXTO, c.getTexto());
			startActivity(i);
		}
		
	}
}
