import { Request, Response } from "express";
import { CreateUserDTO } from "./dto/create.user.dto.js";
import { GetUserDTO } from "./dto/get.user.dto.js";
import { RoleEnum } from "../../generated/prisma/enums.js";
import {
  createUserService,
  validateLoginService,
  promoteUserToAdminService
} from "./user.services.js";
import { generateToken } from "../../utils/jwt.js";

export const createUser = async (
  req: Request<{}, {}, CreateUserDTO>,
  res: Response
) => {
  try {
    const { name, password, email } = req.body;

    if (!name || !password || !email) {
      return res.status(400).json({
        success: false,
        error: "All fields are required!",
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

    return res.status(201).json({
      success: true,
      user: result.user,
    });

  } catch {
    return res.status(500).json({
      success: false,
      error: "Internal server error",
    });
  }
};

export const loginUser = async (
  req: Request<{}, {}, GetUserDTO>,
  res: Response
) => {
  try {
    const { name, password } = req.body;

    if (!name || !password) {
      return res.status(400).json({
        success: false,
        error: "Name and password are required!",
      });
    }

    const user = await validateLoginService(name, password);

    if (!user) {
      return res.status(401).json({
        success: false,
        error: "Invalid credentials",
      });
    }

    const token = generateToken({
      id: user.id,
      name: user.name,
      role: user.role,
    });

    return res.status(200).json({
      success: true,
      token,
    });

  } catch {
    return res.status(500).json({
      success: false,
      error: "Internal server error",
    });
  }
};

export const promoteUserToAdmin = async (
  req: Request,
  res: Response
) => {
  try {
    const { userId, secureKey } = req.body;

    if (!userId || !secureKey) {
      return res.status(400).json({
        success: false,
        error: "userId and secureKey are required",
      });
    }

    const result = await promoteUserToAdminService(
      Number(userId),
      secureKey
    );

    if (!result.success) {
      return res.status(403).json(result);
    }

    return res.status(200).json(result);

  } catch {
    return res.status(500).json({
      success: false,
      error: "Internal server error",
    });
  }
};