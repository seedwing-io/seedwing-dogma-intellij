package io.seedwing.policy.dogma.syntax.highlighting;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.plugins.textmate.language.syntax.highlighting.TextMateHighlighter;
import org.jetbrains.plugins.textmate.language.syntax.lexer.TextMateLexerDataStorage;

import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.ex.util.DataStorage;
import com.intellij.openapi.editor.ex.util.LexerEditorHighlighter;
import com.intellij.openapi.editor.highlighter.EditorHighlighter;
import com.intellij.openapi.fileTypes.EditorHighlighterProvider;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

public class DogmaEditorHighlighterProvider implements EditorHighlighterProvider {
    @Override
    public EditorHighlighter getEditorHighlighter(
            @Nullable Project project,
            @NotNull FileType fileType,
            @Nullable VirtualFile virtualFile,
            @NotNull EditorColorsScheme colors
    ) {
        return new DogmaEditorHighlighter(new DogmaSyntaxHighlighterFactory().getSyntaxHighlighter(project, virtualFile), colors);
    }

    private static final class DogmaEditorHighlighter extends LexerEditorHighlighter {
        private DogmaEditorHighlighter(@Nullable SyntaxHighlighter highlighter, @NotNull EditorColorsScheme colors) {
            super(highlighter != null ? highlighter : new TextMateHighlighter(null), colors);
        }

        @NotNull
        @Override
        protected DataStorage createStorage() {
            return new TextMateLexerDataStorage();
        }
    }
}
