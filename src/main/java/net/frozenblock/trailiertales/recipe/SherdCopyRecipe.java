package net.frozenblock.trailiertales.recipe;

import net.frozenblock.trailiertales.registry.RegisterRecipies;
import net.frozenblock.trailiertales.tag.TrailierItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class SherdCopyRecipe extends CustomRecipe {

	public SherdCopyRecipe(CraftingBookCategory craftingBookCategory) {
		super(craftingBookCategory);
	}

	@Override
	public boolean matches(@NotNull CraftingInput input, Level world) {
		if (!this.canCraftInDimensions(input.width(), input.height())) {
			return false;
		}
		int sherds = 0;
		int bricks = 0;
		for (ItemStack itemStack : input.items()) {
			if (itemStack.is(TrailierItemTags.COPYABLE_SHERDS)) {
				sherds += 1;
			} else if (itemStack.is(TrailierItemTags.POT_BASES)) {
				bricks += 1;
			} else if (!itemStack.is(Items.AIR)) {
				return false;
			}
		}
		return sherds == 1 && bricks == 1;
	}

	@Override @NotNull
	public ItemStack assemble(@NotNull CraftingInput input, HolderLookup.Provider provider) {
		for (ItemStack itemStack : input.items()) {
			if (itemStack.is(TrailierItemTags.COPYABLE_SHERDS)) {
				return new ItemStack(itemStack.getItem(), 2);
			}
		}
		return ItemStack.EMPTY;
	}

	@Override
	public boolean canCraftInDimensions(int i, int j) {
		return i >= 2 && j >= 2;
	}

	@Override @NotNull
	public RecipeSerializer<?> getSerializer() {
		return RegisterRecipies.SHERD_COPY_RECIPE;
	}
}

