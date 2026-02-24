import { Request, Response, NextFunction } from "express";
import { verifyToken } from "../utils/jwt.js";

export interface AuthRequest extends Request {
  user?: any;
}

export function authMiddleware(
  req: AuthRequest,
  res: Response,
  next: NextFunction
) {
  const authHeader = req.headers.authorization;

  if (!authHeader) {
    return res.status(401).json({
      success: false,
      error: "Token not provided",
    });
  }

  const parts = authHeader.split(" ");

  if (parts.length !== 2) {
    return res.status(401).json({
      success: false,
      error: "Token format invalid",
    });
  }

  const [, token] = parts;

  try {
    const decoded = verifyToken(token);
    req.user = decoded;

    next();
  } catch {
    return res.status(401).json({
      success: false,
      error: "Invalid or expired token",
    });
  }
}