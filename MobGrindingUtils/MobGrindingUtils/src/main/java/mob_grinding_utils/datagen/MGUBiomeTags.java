package mob_grinding_utils.datagen;

import mob_grinding_utils.ModTags;
import mob_grinding_utils.Reference;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public class MGUBiomeTags extends BiomeTagsProvider {
    public MGUBiomeTags(DataGenerator generator, CompletableFuture<HolderLookup.Provider> something, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), something, Reference.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(@Nonnull HolderLookup.Provider pointless) {
        tag(ModTags.Biomes.PASSIVE_OVERRIDE).addTags(Tags.Biomes.IS_CAVE);
        tag(ModTags.Biomes.PASSIVE_OVERRIDE).addTags(BiomeTags.IS_RIVER);

        getOrCreateRawBuilder(ModTags.Biomes.HOSTILE_OVERRIDE);
    }
}
