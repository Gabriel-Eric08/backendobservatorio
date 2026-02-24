import { Router } from "express";
import { createUser, loginUser, promoteUserToAdmin } from "./user.controller.js";

const userRoutes = Router();

userRoutes.post("/register", createUser);
userRoutes.post("/login", loginUser)
userRoutes.post("/promote", promoteUserToAdmin)

export default userRoutes;