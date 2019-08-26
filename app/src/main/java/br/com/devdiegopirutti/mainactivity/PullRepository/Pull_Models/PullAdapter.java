package br.com.devdiegopirutti.mainactivity.PullRepository.Pull_Models;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import br.com.devdiegopirutti.mainactivity.R;
import br.com.devdiegopirutti.mainactivity.models.BasePRResponse;
import de.hdodenhof.circleimageview.CircleImageView;

public class PullAdapter extends RecyclerView.Adapter<PullAdapter.ViewHolder> {


    private ArrayList<BasePRResponse> arrayList;

    public PullAdapter() {
        arrayList = new ArrayList<>();
    }

    public void adicionarLista(List<BasePRResponse> lista) {
        arrayList.addAll(lista);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pull_user_layout, parent, false);
        return new PullAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tituloPR;
        private TextView descricaoRepositorio;
        private TextView userName;
        private TextView nomeSobre;
        private TextView opened;
        private TextView closed;
        private CircleImageView autorFoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            opened = itemView.findViewById(R.id.num_opened);
            closed = itemView.findViewById(R.id.num_closed);
            tituloPR = itemView.findViewById(R.id.title_pull);
            descricaoRepositorio = itemView.findViewById(R.id.body_pull);
            userName = itemView.findViewById(R.id.userPull);
            nomeSobre = itemView.findViewById(R.id.complete_name_pull);
            autorFoto = itemView.findViewById(R.id.imageViewPull);


        }

        void bind(final BasePRResponse item) {
            // opened.setText(String.valueOf(item.getHead().getRepo().getOpenIssuesCount()));
            //closed.setText(item.getUser();
            tituloPR.setText(item.getTitle());
            descricaoRepositorio.setText(item.getBody());
            userName.setText(item.getUser().getLogin());
            nomeSobre.setText(item.getHead().getRepo().getFullName());

            Picasso.get()
                    .load(item.getUser().getAvatarUrl())
                    .into(autorFoto);

            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(item.getHtmlUrl()));
                    v.getContext().startActivity(i);
                }
            });

        }
    }
}
