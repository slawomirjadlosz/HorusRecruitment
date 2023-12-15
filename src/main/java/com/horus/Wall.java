package com.horus;

import java.util.*;

public class Wall implements Structure {

    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        for (Block block : blocks) {
            Optional<Block> temporaryBlock = Optional.ofNullable(block);
            if (temporaryBlock.isPresent()) {
                if(block instanceof CompositeBlock) {
                    for(Block blockFromCompositeBlock : ((CompositeBlock) block).getBlocks()) {
                        if (blockFromCompositeBlock.getColor().equals(color)) {
                            return temporaryBlock;
                        }
                    }
                } else if (block.getColor().equals(color)) {
                    return temporaryBlock;
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> blocksOfSameMaterial = new LinkedList<>();
        for (Block block : blocks) {
            if(block instanceof CompositeBlock) {
                for(Block blockFromCompositeBlock : ((CompositeBlock) block).getBlocks()) {
                    if (blockFromCompositeBlock.getMaterial().equals(material)) {
                        blocksOfSameMaterial.add(blockFromCompositeBlock);
                    }
                }
            } else if (block.getMaterial().equals(material)) {
                blocksOfSameMaterial.add(block);
            }
        }
        return blocksOfSameMaterial;
    }

    @Override
    public int count() {
        int counter = 0;
        for(Block block : blocks) {
            if(block instanceof CompositeBlock) {
                counter += ((CompositeBlock) block).getBlocks().size();
            } else {
                counter++;
            }
        }
        return counter;
    }
}
