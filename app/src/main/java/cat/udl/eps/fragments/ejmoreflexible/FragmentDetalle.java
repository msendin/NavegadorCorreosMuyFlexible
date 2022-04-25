package cat.udl.eps.fragments.ejmoreflexible;

import android.os.Bundle;
//import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

  public class FragmentDetalle extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
			                 ViewGroup container, 
			                 Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_detalle, container, false);
	}
	
	public void mostrarDetalle(String texto) {
		TextView txtDetalle = 
				requireView().findViewById(R.id.TxtDetalle);
		txtDetalle.setText(texto);
	}
  }
