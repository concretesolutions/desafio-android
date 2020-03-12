package br.com.ribamar.concrete.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.ribamar.concrete.R;
import br.com.ribamar.concrete.model.pojos.requests.Request;
import br.com.ribamar.concrete.view.interfaces.RequestOnClick;


public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {

    private List<Request> requests;
    private RequestOnClick listener;

    public RequestAdapter(List<Request> requests, RequestOnClick listener) {
        this.requests = requests;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_view_request,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Request request = requests.get(position);
        holder.bind(request);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(request);
            }
        });
    }
    public void setUpdate(List<Request> requests) {
        if (this.requests.isEmpty()) {
            this.requests = requests;
        } else {
            this.requests.addAll(requests);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nome;
        private TextView descricao;
        private TextView usuario;
        private TextView nomeSobrenome;
        private ImageView foto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.textViewRepository);
            descricao = itemView.findViewById(R.id.textViewDescricao);
            usuario = itemView.findViewById(R.id.textViewUsername);
            nomeSobrenome = itemView.findViewById(R.id.textViewNomeSobrenome);
            foto = itemView.findViewById(R.id.imageViewProfile);
        }

        public void bind(Request request) {
            nome.setText(request.getTitle());
            descricao.setText(request.getBody());
            usuario.setText(request.getUser().getLogin());
            nomeSobrenome.setText(request.getHead().getRepo().getOwner().getLogin());
            Picasso.get().load(request.getUser().getAvatarUrl() + ".jpg").into(foto);
        }
    }
}
