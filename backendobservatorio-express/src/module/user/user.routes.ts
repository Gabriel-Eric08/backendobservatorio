import { Router } from "express";
import { createUser, getUser } from "./user.controller.js";

const userRoutes = Router();

userRoutes.post("/register", createUser);
userRoutes.post("/id", getUser)

export default userRoutes;