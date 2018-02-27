package com.valkryst.VTerminal.builder;

import com.valkryst.VTerminal.component.ProgressBar;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class ProgressBarBuilder extends ComponentBuilder<ProgressBar> {
    /** The character that represents an incomplete cell. */
    private char incompleteCharacter;
    /** The character that represents a complete cell. */
    private char completeCharacter;

    @Override
    public ProgressBar build() {
        checkState();
        return new ProgressBar(this);
    }

    @Override
    public void reset() {
        super.reset();

        super.setDimensions(10, 1);

        incompleteCharacter = '█';
        completeCharacter = '█';
    }
}