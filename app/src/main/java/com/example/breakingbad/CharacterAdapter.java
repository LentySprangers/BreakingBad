package com.example.breakingbad;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private final String TAG = getClass().getSimpleName();
    private List<Character> mCharacterList;



    public CharacterAdapter(List<Character> characterList) {
        Log.d(TAG, "Constructor was called");
        this.mCharacterList = characterList;
    }



    public class CharacterViewHolder extends RecyclerView.ViewHolder {

        private final String TAG = getClass().getSimpleName();
        //following attributes are public because they are in a nested class
        public TextView characterName;
        public TextView nickname;
        public TextView status;
        public ImageView img;


        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);

            Log.d(TAG, "constructor was called");

            characterName = (TextView) itemView.findViewById(R.id.character_list_item_name);
            nickname = (TextView) itemView.findViewById(R.id.character_list_item_nickname);
            status = (TextView) itemView.findViewById(R.id.character_list_item_status);
            img = (ImageView) itemView.findViewById(R.id.character_list_item_img);

            Button mShowDetailsButton = (Button) itemView.findViewById(R.id.show_details_button);


            mShowDetailsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick was called");

//                    Intent intent = new Intent(this, DetailsActivity.class);
//                    startActivity(intent);
                }
            });
        }


    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder was called");

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.character_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder was called");

        Character character = mCharacterList.get(position);

        holder.characterName.setText(character.getName());
        holder.nickname.setText(character.getNickname());
        holder.status.setText(character.getStatus());
        Picasso.get().load(character.getImg()).resize(250,300).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return mCharacterList.size();
    }
}
