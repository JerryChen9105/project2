package au.edu.unimelb.swen.oop.mokepon.event;

import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.state.GameState;

public record ChooseMokeponEvent(Mokepon selectedMokepon, GameState state) {}