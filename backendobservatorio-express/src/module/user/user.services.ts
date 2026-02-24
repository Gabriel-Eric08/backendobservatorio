import { RoleEnum } from "../../generated/prisma/enums.js";
import { prisma } from "../../lib/prisma.js";

export async function createUserService(
  name: string,
  password: string,
  email: string,
  role: RoleEnum
) {
  try {
    const user = await prisma.user.create({
      data: {
        name,
        email,
        password,
        role,
      },
    });

    return {
      success: true,
      user,
    };
  } catch (error) {
    return {
      success: false,
      user: null,
    };
  }
}

export async function getUserService(
  name: string,
  password: string
) {
  try {
    const user = await prisma.user.findFirst({
      where: {
        name,
        password,
      },
    });

    if (!user) {
      return {
        success: false,
        user: null,
      };
    }

    return {
      success: true,
      user,
    };
  } catch (error) {
    console.log(error);
    return {
      success: false,
      user: null,
    };
  }
}

export async function validateLoginService(
  name: string,
  password: string
) {
  try {
    const user = await prisma.user.findFirst({
      where: {
        name,
        password,
      },
    });

    return user ?? null;
  } catch (error) {
    console.error(error);
    return null;
  }
}
export async function promoteUserToAdminService(
  userId: number,
  secureKey: string
) {
  try {
    if (!process.env.SECURE_KEY) {
      return {
        success: false,
        error: "Secure key not configured",
      };
    }

    if (secureKey !== process.env.SECURE_KEY) {
      return {
        success: false,
        error: "Invalid secure key",
      };
    }

    const user = await prisma.user.update({
      where: { id: userId },
      data: { role: RoleEnum.ADMIN },
    });

    return {
      success: true,
      user,
    };
  } catch (error) {
    console.error(error);

    return {
      success: false,
      error: "User not found or update failed",
    };
  }
}