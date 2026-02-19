import { Router } from "express";
import { createUser } from "./user.controller.js";

const userRoutes = Router();

userRoutes.post("/register", createUser);

export default userRoutes;