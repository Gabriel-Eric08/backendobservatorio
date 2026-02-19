import { Request, Response } from "express";
import { CreateUserDTO } from "./dto/create.user.dto.js";
import { RoleEnum } from "../../generated/prisma/enums.js";
import { createUserService } from "./user.services.js";

export const createUser = async (
    req: Request<{}, {}, CreateUserDTO>,
    res: Response
) => {
    try {
        const { name, password, email } = req.body;

        if (!name || !password || !email) {
            return res.status(400).json({
                success: false,
                error: "All fields are required!"
            });
        }

        const result = await createUserService(
            name,
            password,
            email,
            RoleEnum.USER
        );

        if (!result.success) {
            return res.status(500).json(result);
        }

        return res.status(201).json(result);

    } catch (error) {
        return res.status(500).json({
            success: false,
            error: "Internal server error"
        });
    }
};
