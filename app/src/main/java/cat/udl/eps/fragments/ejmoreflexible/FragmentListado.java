package cat.udl.eps.fragments.ejmoreflexible;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

//import android.app.ListFragment;

public class FragmentListado extends Fragment {
	
	private final Correo[] datos =
	    	new Correo[]{
	    		new Correo("Persona 1", "Asunto del correo 1", "Texto del correo 1"),
	    		new Correo("Persona 2", "Asunto del correo 2", "Texto del correo 2"),
	    		new Correo("Persona 3", "Asunto del correo 3", "Texto del correo 3"),
	    		new Correo("Persona 4", "Asunto del correo 4", "Texto del correo 4"),
	    		new Correo("Persona 5", "Asunto del correo 5", "Texto del correo 5")};
	
	private ListView lstListado;
	
	private CorreosListener listener;
	
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.fragment_listado, container, false);
	}
	
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle state) {
		super.onViewCreated(view, state);
		lstListado = requireView().findViewById(R.id.LstListado);
		lstListado.setAdapter(new AdaptadorCorreos(this));
		lstListado.setOnItemClickListener((list, view1, pos, id) -> {
			if (listener!=null) {
				listener.onCorreoSeleccionado(
						(Correo)lstListado.getAdapter().getItem(pos));
			}
		});
	}
	
	
	@Override
	public void onAttach(@NonNull Context context) {
		super.onAttach(context);
		try {
			listener = (CorreosListener) context;
		}
		catch (ClassCastException e) {
			throw new ClassCastException(context + " must implement OnCorreosListener");
		}
	}
			
			
	class AdaptadorCorreos extends ArrayAdapter<Correo> {
    	
    	Activity context;
    	
    	AdaptadorCorreos(FragmentListado fragmentListado) {
    		super(fragmentListado.requireActivity(), R.layout.listitem_correo, datos);
    		this.context = fragmentListado.requireActivity();
    	}

    	@androidx.annotation.NonNull
    	public View getView(int position, View convertView, @androidx.annotation.NonNull ViewGroup parent) {
			LayoutInflater inflater = context.getLayoutInflater();
			@SuppressLint({"InflateParams", "ViewHolder"}) View item = inflater.inflate(R.layout.listitem_correo, null);
			
			TextView lblDe = item.findViewById(R.id.LblDe);
			lblDe.setText(datos[position].getDe());
			
			TextView lblAsunto = item.findViewById(R.id.LblAsunto);
			lblAsunto.setText(datos[position].getAsunto());
			
			return(item);
		}
    }
	
	public interface CorreosListener {
		void onCorreoSeleccionado(Correo c);
	}
	
	public void setCorreosListener(CorreosListener listener) {
		this.listener=listener;
	}
}
