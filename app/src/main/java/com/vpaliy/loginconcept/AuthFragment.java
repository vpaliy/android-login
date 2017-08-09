package com.vpaliy.loginconcept;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import com.transitionseverywhere.*;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import butterknife.ButterKnife;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import butterknife.OnClick;
import butterknife.BindView;

public abstract class AuthFragment extends Fragment {

    protected Callback callback;

    @BindView(R.id.caption)
    protected VerticalTextView caption;

    @BindView(R.id.root)
    protected ViewGroup parent;

    protected boolean lock;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(authLayout(),container,false);
        ButterKnife.bind(this,root);
        KeyboardVisibilityEvent.setEventListener(getActivity(), isOpen -> {
            callback.scale(isOpen);
            if(!isOpen){
                clearFocus();
            }
        });
        return root;
    }

    public void setCallback(@NonNull Callback callback) {
        this.callback = callback;
    }

    @LayoutRes
    public abstract int authLayout();
    public abstract void fold();
    public abstract void clearFocus();

    @OnClick(R.id.root)
    public void unfold(){
        if(!lock) {
            caption.setVerticalText(false);
            caption.requestLayout();
            Rotate transition = new Rotate();
            transition.setStartAngle(-90f);
            transition.setEndAngle(0f);
            transition.addTarget(caption);
            TransitionSet set=new TransitionSet();
            set.setDuration(getResources().getInteger(R.integer.duration));
            ChangeBounds changeBounds=new ChangeBounds();
            set.addTransition(changeBounds);
            set.addTransition(transition);
            TextSizeTransition sizeTransition=new TextSizeTransition();
            sizeTransition.addTarget(caption);
            set.addTransition(sizeTransition);
            set.setOrdering(TransitionSet.ORDERING_TOGETHER);
            caption.post(()->{
                TransitionManager.beginDelayedTransition(parent, set);
                caption.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(R.dimen.unfolded_size));
                caption.setTextColor(ContextCompat.getColor(getContext(),R.color.color_label));
                caption.setTranslationX(0);
                ConstraintLayout.LayoutParams params = getParams();
                params.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
                params.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
                params.verticalBias = 0.78f;
                caption.setLayoutParams(params);
            });
            callback.show(this);
            lock=true;
        }
    }

    protected ConstraintLayout.LayoutParams getParams(){
        return ConstraintLayout.LayoutParams.class.cast(caption.getLayoutParams());
    }

    interface Callback {
        void show(AuthFragment fragment);
        void scale(boolean hasFocus);
    }
}
