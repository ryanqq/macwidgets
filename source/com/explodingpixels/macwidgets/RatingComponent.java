package com.explodingpixels.macwidgets;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.explodingpixels.data.Rating;
import com.explodingpixels.data.RatingChangeListener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class RatingComponent {

    private JPanel fComponent = new JPanel();

    private Rating fRating;

    private boolean fSelected;

    private boolean fFocused = true;

    private List<JLabel> ratingIndicators = new ArrayList<JLabel>();

    private List<RatingChangeListener> fListeners =
            new ArrayList<RatingChangeListener>();

    private static ImageIcon FOCUSED_SELECTED_STAR =
            new ImageIcon(ITunesRatingTableCellRenderer.class.getResource(
                    "/com/explodingpixels/macwidgets/images/itunes_star_focused_selected.png"));

    private static ImageIcon UNFOCUSED_SELECTED_STAR =
            new ImageIcon(ITunesRatingTableCellRenderer.class.getResource(
                    "/com/explodingpixels/macwidgets/images/itunes_star_unfocused_selected.png"));

    private static ImageIcon UNSELECTED_STAR =
            new ImageIcon(ITunesRatingTableCellRenderer.class.getResource(
                    "/com/explodingpixels/macwidgets/images/itunes_star_unselected.png"));

    private static ImageIcon UNFOCUSED_DOT =
            new ImageIcon(ITunesRatingTableCellRenderer.class.getResource(
                    "/com/explodingpixels/macwidgets/images/itunes_dot_unfocused.png"));

    private static ImageIcon FOCUSED_DOT =
            new ImageIcon(ITunesRatingTableCellRenderer.class.getResource(
                    "/com/explodingpixels/macwidgets/images/itunes_dot_focused.png"));

    RatingComponent(Rating rating) {
        fRating = rating;
        buildRatingPanel();
    }

    private void buildRatingPanel() {

        // definte the FormLayout columns and rows.
        FormLayout layout = new FormLayout(
                "","fill:p:grow");
        // create the cell constraints to use in the layout.
        CellConstraints cc = new CellConstraints();
        // create the builder with our panel as the component to be filled.
        PanelBuilder builder = new PanelBuilder(layout, fComponent);

        fComponent.setOpaque(true);
        for (int i=0; i< Rating.values().length-1; i++) {
            RatingLabel label = new RatingLabel(i);
            ratingIndicators.add(label);

            builder.appendColumn("p");
            builder.add(label, cc.xy(builder.getColumn(), 1));
            builder.nextColumn();

        }
    }

    private MouseListener createMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO do something;
            }
        };
    }

    public JComponent getComponent() {
        return fComponent;
    }

    public void setSelected(boolean selected) {
        fSelected = selected;
    }

    public void setFocused(boolean focused) {
        fFocused = focused;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Custom class to return icon for rating indicator based on its position
    // in the larger component and the current rating.
    ///////////////////////////////////////////////////////////////////////////

    private class RatingLabel extends JLabel {

        private int fPosition;

        private RatingLabel(int position) {
            fPosition = position;
        }

        @Override
        public Icon getIcon() {
            Icon retVal = null;

            if (fRating == null || fRating == Rating.NO_RATING) {
                retVal = null;
            } else if (fPosition < fRating.ordinal()) {
                retVal = getStarIcon();
            } else if (fSelected) {
                retVal = getDotIcon();
            }

          return retVal;
        }

        private Icon getStarIcon() {
            Icon retVal;

            if (!fSelected) {
                retVal = UNSELECTED_STAR;
            } else if (fFocused) {
                retVal = FOCUSED_SELECTED_STAR;
            } else {
                retVal = UNFOCUSED_SELECTED_STAR;
            }

            return retVal;
        }

        private Icon getDotIcon() {
            Icon retVal = null;

            if (fFocused) {
                retVal = FOCUSED_DOT;
            } else {
                retVal = UNFOCUSED_DOT;
            }

            return retVal;
        }

    }

    ///////////////////////////////////////////////////////////////////////////
    // Rating change listener support.
    ///////////////////////////////////////////////////////////////////////////

    public void addRatingChangeListener(RatingChangeListener listener) {
        fListeners.add(listener);
    }

    public void removeRatingChangeListener(RatingChangeListener listener) {
        fListeners.remove(listener);
    }

}